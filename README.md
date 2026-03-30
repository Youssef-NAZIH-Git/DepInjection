# Injection de Dépendances — Java & Spring IOC

> Travaux pratiques autour du patron de conception **Inversion de Contrôle** (IoC),
> implémentés en Java pur puis avec Spring, et complétés par un mini-framework maison.

---

## Structure du projet

| Dossier | Contenu |
|---|---|
| [`Partie1/DepInjection`](https://github.com/Youssef-NAZIH-Git/DepInjection/tree/master/Partie1/DepInjection) | Injection de dépendances classique et avec Spring |
| [`Partie2/depinj-framework`](https://github.com/Youssef-NAZIH-Git/DepInjection/tree/master/Partie2/depinj-framework) | Mini-framework IoC inspiré de Spring |

---

## Partie 1 — Injection de dépendances

### Conception

| Interface / Classe | Rôle |
|---|---|
| `IDao` | Contrat d'accès aux données — méthode `getData()` |
| `DaoImpl` | Implémentation concrète de `IDao` |
| `IMetier` | Contrat métier — méthode `calcul()` |
| `MetierImpl` | Implémentation métier en **couplage faible** avec `IDao` |

### Méthodes d'injection

| Méthode | Description |
|---|---|
| Instanciation statique | Câblage manuel des objets dans le code |
| Instanciation dynamique | Chargement par réflexion Java (`Class.forName`) |
| Spring XML | Configuration déclarative via `applicationContext.xml` |
| Spring Annotations | `@Component`, `@Autowired`, `@ComponentScan` |

---

## Partie 2 — Mini-framework IoC

Framework d'injection de dépendances développé from scratch, reprenant les principes fondamentaux de Spring IOC.

### Modes de configuration

| Mode | Technologie |
|---|---|
| Fichier XML | JAXB — Object/XML Mapping (OXM) |
| Annotations | Annotations personnalisées inspectées par réflexion |

### Types d'injection supportés

| Type | Mécanisme |
|---|---|
| Constructeur | Injection via les paramètres du constructeur |
| Setter | Injection via les méthodes `set*()` |
| Attribut | Accès direct au champ (`Field`) par réflexion |

---

## Technologies

| Outil | Usage |
|---|---|
| Java | Langage principal |
| Spring Framework | Conteneur IoC (Partie 1) |
| JAXB / OXM | Parsing XML pour le mini-framework |
| Réflexion Java | Coeur du mini-framework |
| IntelliJ IDEA | Environnement de développement |

---

## Lancer le projet

```bash
# Cloner le dépôt
git clone https://github.com/Youssef-NAZIH-Git/DepInjection.git

# Ouvrir le dossier souhaité dans IntelliJ IDEA
# et lancer la classe Main correspondante
```

---

## Auteur

**[Youssef NAZIH](https://github.com/Youssef-NAZIH-Git)** — [github.com/Youssef-NAZIH-Git/DepInjection](https://github.com/Youssef-NAZIH-Git/DepInjection)
