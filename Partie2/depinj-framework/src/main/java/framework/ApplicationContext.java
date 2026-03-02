package framework;

public interface ApplicationContext {
    Object getBean(String name);
    Object getBean(Class<?> classVar);
}
