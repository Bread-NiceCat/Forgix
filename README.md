![# Forgix](https://user-images.githubusercontent.com/37855219/177012300-5d402b36-3393-4cbf-9d45-d99d6800c91f.png)
---
A Gradle plugin/an [Architectury](https://github.com/architectury) addon to merge [Fabric](http://fabricmc.net/) (also [Quilt](https://quiltmc.org/)) &amp; [Forge](https://files.minecraftforge.net/net/minecraftforge/forge/) jars into one! 𝘸𝘢𝘩𝘩 𝘵𝘦𝘤𝘩𝘯𝘰𝘭𝘰𝘨𝘺

## Usage:
_This would probably be moved to wikis at some point and will have better documentation_
#### 
Note: You'll have to do all of these in the root build.gradle
### Applying the plugin:
#### Groovy
Using the [plugins DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block):

```groovy
plugins {
    id "io.github.pacifistmc.forgix" version "1.1.1"
}
```

Using the [legacy plugin application](https://docs.gradle.org/current/userguide/plugins.html#sec:old_plugin_application):
<details><summary>Click to View</summary>

```groovy
buildscript {
    repositories {
        maven {
            url "https://plugins.gradle.org/m2/"
        }
    }
    dependencies {
        classpath "io.github.pacifistmc.forgix:Forgix:1.1.1"
    }
}

apply plugin: "io.github.pacifistmc.forgix"
```
</details>

#### Kotlin

Using the [plugins DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block):

```kotlin
plugins {
    id("io.github.pacifistmc.forgix") version "1.1.1"
}
```

Using the [legacy plugin application](https://docs.gradle.org/current/userguide/plugins.html#sec:old_plugin_application):
<details><summary>Click to View</summary>

```kotlin
buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("io.github.pacifistmc.forgix:Forgix:1.1.1")
    }
}

apply(plugin = "io.github.pacifistmc.forgix")
```
</details>

### Configuration:
By default just running the task "mergeJars" should work if you've made your mod through the [Architectury Template](https://github.com/architectury/architectury-templates). Though don't forget to build the jars first!
```groovy
forgix {
    group = "org.example.mod" // This is the common group of the mod which by default in Architectury Template it's defined as "maven_group" in your gradle.properties. If this property is not defined then by default it'll fetch the group from the maven_group property in your gradle.properties
    mergedJarName = "example-mod" // This is the name of the merged jar. If this property is not defined then by default it'll fetch the "archives_base_name" property with the "mod_version" property in your gradle.properties.
    outputDir = "build/libs/Merged" // This is the output directory of the merged jar from the root project. If this property is not defined then by default it's set to "Merged".
    
    forge {
        projectName = "forge" // This is the name of the forge project. If this property is not defined then by default it'll set to "forge" since that's the name the Architectury Template uses.
        jarLocation = "build/libs/example-mod.jar" // This is the location of the forge jar from the forge project. If this property is not defined then by default it fetches the jar with the shortest name.

        additionalRelocate "org.my.lib" "forge.org.my.lib" // This is an important one to know. This is how you can remap additional packages such as libraries and stuff.
        additionalRelocate "org.my.lib.another" "forge.org.my.lib.another"
        
        mixin "forge.mixins.json" // This is in case if we didn't auto detect the forge mixins.
        mixin "forge.mixins.another.json"
    }
    
    fabric {
        projectName = "fabric" // This is the name of the fabric project. If this property is not defined then by default it'll set to "fabric" since that's the name the Architectury Template uses.
        jarLocation = "build/libs/example-mod.jar" // This is the location of the fabric jar from the fabric project. If this property is not defined then by default it fetches the jar with the shortest name.
        
        additionalRelocate "org.my.lib" "fabric.org.my.lib" // This is an important one to know. This is how you can remap additional packages such as libraries and stuff.
        additionalRelocate "org.my.lib.another" "fabric.org.my.lib.another"
    }
    
    quilt {
        projectName = "quilt" // This is the name of the quilt project. If this property is not defined then by default it'll set to "quilt" since that's the name the Architectury Template uses.
        jarLocation = "build/libs/example-mod.jar" // This is the location of the quilt jar from the quilt project. If this property is not defined then by default it fetches the jar with the shortest name.
        
        additionalRelocate "org.my.lib" "quilt.org.my.lib" // This is an important one to know. This is how you can remap additional packages such as libraries and stuff.
        additionalRelocate "org.my.lib.another" "quilt.org.my.lib.another"
    }
}
```
#
#### How did I come up with the name "Forgix"?
Well an AI generated it for me
_and also gave me the logo for it_
