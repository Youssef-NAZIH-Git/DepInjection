package framework;

import framework.annotations.Autowired;
import framework.annotations.Component;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnnotationApplicationContext implements ApplicationContext {

    // Bean storage
    private final Map<String, Object> beans = new HashMap<>();


    public AnnotationApplicationContext(String basePackage) {
        try{
            scanPackage(basePackage);
            injectDependencies();
        } catch (Exception e) {
            System.err.println("Error in the annotation application context constructor: " + e.getMessage());
        }
    }


    /// Iterates through classes, then calls registerBean for each class
    private void scanPackage(String basePackage) throws Exception {
        String path = basePackage.replace('.', '/');
        URL url = getClass().getClassLoader().getResource(path);
        File dir = new File(Objects.requireNonNull(url).toURI());

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.getName().endsWith(".class")) {
                registerBean(basePackage, file);
            }
        }
    }


    /// Only components are registered as beans
    /// If the component specified a name it gets used
    /// Registers beans as (name, instance) in the map
    /// TODO: Cleanup because it's hard to read ts
    private void registerBean(String basePackage, File file) throws Exception {
        String className = basePackage + "." + file.getName().replace(".class", "");
        Class<?> classVar = Class.forName(className);

        if (classVar.isAnnotationPresent(Component.class)) {
            Component comp = classVar.getAnnotation(Component.class);
            String beanName = comp.value().isEmpty() ? getDefaultBeanName(classVar) : comp.value();
            beans.put(beanName, createInstance(classVar));
        }
    }


    /// Checks if class has an autowired constructor
    /// If so it resolves dependencies
    /// Otherwise calls empty constructor
    private Object createInstance(Class<?> classVar) throws Exception {
        for (Constructor<?> constructor : classVar.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Autowired.class)) {
                Object[] dependencies = resolveDependencies(constructor.getParameterTypes());
                return constructor.newInstance(dependencies);
            }
        }
        return classVar.getDeclaredConstructor().newInstance();
    }


    /// This is split into 2 because it's annoying
    /// Checks if the dependency is resolvable using an existing bean
    private Object[] resolveDependencies(Class<?>[] types) throws Exception {
        Object[] dependencies = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            dependencies[i] = resolveDependency(types[i]);
        }
        return dependencies;
    }

    private Object resolveDependency(Class<?> type) {
        for (Object bean : beans.values()) {
            if (type.isAssignableFrom(bean.getClass()))
                return bean;
        }
        throw new RuntimeException("No bean found for type: " + type.getName());
    }


    /// Again split into 2 because it's annoying
    /// Iterates through beans and checks if they have an autowired field or setter
    /// If so, it injects an available bean within it
    private void injectDependencies() throws Exception {
        for (Object bean : beans.values()) {
            injectFields(bean);
            injectMethods(bean);
        }
    }

    private void injectFields(Object bean) throws Exception {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = resolveDependency(field.getType());
                field.setAccessible(true);
                field.set(bean, dependency);
            }
        }
    }

    private void injectMethods(Object bean) throws Exception {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Autowired.class)) {
                Object dependency = resolveDependency(method.getParameterTypes()[0]);
                method.invoke(bean, dependency);
            }
        }
    }


    /// Helper functions
    private String getDefaultBeanName(Class<?> classVar) {
        return Character.toLowerCase(classVar.getSimpleName().charAt(0)) + classVar.getSimpleName().substring(1);
    }

    @Override
    public Object getBean(String name) {
        return beans.get(name);
    }

    @Override
    public Object getBean(Class<?> type) {
        return resolveDependency(type);
    }
}