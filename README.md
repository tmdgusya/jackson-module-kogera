jackson-module-kogera
====
`jackson-module-kogera` is an experimental project to develop `jackson-module-kotlin`.  
This project has the following features compared to `jackson-module-kotlin`.

- Lightweight
- high-performance
  - Fast deserialization
  - Smaller memory consumption
- More `Kotlin` friendly behavior

This project is experimental, but passes all the tests implemented in `jackson-module-kotlin` except for the intentional incompatibility.

# Features of `jackson-module-kogera`
The main feature of `jackson-module-kogera` is that it replaces `kotlin-reflect` with `kotlinx.metadata.jvm`.  
As of `1.7.21`, `kotlin-reflect` is a huge library(3MB), and replacing it with `kotlinx.metadata.jvm`(1MB) makes it lightweight.

Several performance improvements have also been made.
First, by implementing the equivalent of https://github.com/FasterXML/jackson-module-kotlin/pull/439, deserialization is now up to three times faster, depending on the use case.  
The cache has also been reorganized based on [benchmark results](https://github.com/ProjectMapK/kogera-benchmark) to achieve smaller memory consumption.  
The performance degradation when the `strictNullChecks` option is enabled is also [greatly reduced](https://github.com/ProjectMapK/jackson-module-kogera/pull/44).

The next main feature is `value class` support.  
The `jackson-module-kogera` supports many use cases for `value class`, including deserialization.  
See [here](./docs/AboutValueClassSupport.md) for basic policies and notes on handling `value class`.

[Here](./docs/FixedIssues.md) is a list of issues that are not resolved in `jackson-module-kotlin` but are or will be resolved in `kogera`.

## About intentional destructive changes
This project makes several disruptive changes to achieve more `Kotlin-like` behavior.  
Details are summarized in [KogeraSpecificImplementations](./docs/KogeraSpecificImplementations.md).

# Compatibility
- `jackson 2.14.2`
- `Java 8+`
- `Kotlin 1.7.21+`

## About compatibility checks
Compatibility checks for `Java` and `Kotlin` are done by `CI` grid tests.

The `Java` test covers all currently supported LTS versions and the latest versions.  
Currently 8,11,17 and 19 are covered.

`Kotlin` is tested on the latest patch version and the latest `Beta` or `RC` version within each minor version since 1.7.21.  
Currently 1.7.21, 1.8.10 and 1.8.20-Beta are covered.  
I hope to lower this version in the future, but currently the minimum `Kotlin` version that can be supported is 1.7 due to `kotlinx-metadata-jvm` constraints.

# Installation
The package is temporarily published in `JitPack`.  
Please refer to `jitpack.io` for the released version.

[ProjectMapK / jackson\-module\-kogera](https://jitpack.io/#ProjectMapK/jackson-module-kogera)

```kotlin
repositories {
    // ...

    maven { setUrl("https://jitpack.io") }
}

dependencies {
  // ...

  implementation("com.github.ProjectMapK:jackson-module-kogera:${version}")
}
```

## Migration in existing projects
**The following descriptions are for the `alpha` version.**  
**I plan to change the package and module names when I move to the `beta` version(see https://github.com/FasterXML/jackson-module-kotlin/issues/450#issuecomment-1384788717).**

When replacing `jackson-module-kotlin` in an existing project, please replace the dependencies of `jackson-module-kotlin` with `jackson-module-kogera`.  
Since the package/module name of `jackson-module-kogera` is the same as that of `jackson-module-kotlin`, it is basically possible to migrate by simply replacing dependencies (although there is a possibility of compile errors due to some destructive changes).

`. /gradlew dependencies` and if `jackson-module-kotlin` does not appear, you have successfully migrated.  
If `jackson-module-kotlin` is still there, please `exclude` it from the dependencies.  
At least for the `Spring` project at hand, I have confirmed that this method works fine up to the `Jackson2ObjectMapperBuilder` auto-configuration.

If you find any problems, it would be appreciated if you could share them in an `issue`.

# About the future
Currently, this project is in `alpha`.  
After the following features are implemented, this project will be moved to the `beta` version if there is enough demand for it.

- ~~Deserialization support for `value class`~~
  - Support for policy decisions and basic use cases was completed in #68, only edge cases and hard-to-fix issues remain
- ~~Support for less than `Kotlin 1.6.x`(including grid test building with `CI`)~~
  - Due to limitations of `kotlinx-metadata-jvm`, only 1.7 or later is supported.
  - However, it will be pulled down when possible.
- Rename module and package(see https://github.com/FasterXML/jackson-module-kotlin/issues/450#issuecomment-1384788717).

# About license
This project is based on `jackson-module-kotlin`, so the license follows `jackson-module-kotlin`.  
The current license is `Apache License 2.0`.

[jackson\-module\-kotlin/LICENSE at 2\.14 · FasterXML/jackson\-module\-kotlin](https://github.com/FasterXML/jackson-module-kotlin/blob/2.14/LICENSE)

# About `Kogera`
`Kogera` is the Japanese name for `Japanese pygmy woodpecker`.  
This bird is the smallest woodpecker in Japan.  
