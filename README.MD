# KMP-BenchmarIndentityHashCode

Kotlin Multiplatform Library

### Publish to MavenCentral

1) Registering a Sonatype account as described here: 
   https://dev.to/kotlin/how-to-build-and-publish-a-kotlin-multiplatform-library-going-public-4a8k
2) Add developer id, name, email and the project url to
   `/convention-plugins/src/main/kotlin/convention.publication.gradle.kts`
3) Add the secrets to `local.properties`:

```
signing.keyId=...
signing.password=...
signing.secretKeyRingFile=...
ossrhUsername=...
ossrhPassword=...
```

4) Run `./gradlew :shared:publishAllPublicationsToSonatypeRepository`

### Build platform artifacts

#### JVM jar

- Run `./gradlew :shared:jvmJar`
- Output: `/shared/build/libs/shared-jvm-1.0.jar`

#### JS file

- Run `./gradlew :shared:jsBrowserProductionWebpack`
- Output: `/shared/build/dist/js/productionExecutable/shared.js`

#### macOS Framework

- Run `./gradlew :shared:linkReleaseFrameworkMacosArm64`
- Output: `/shared/build/bin/macosArm64/releaseFramework/shared.framework`

#### Linux static library

- Run `./gradlew :shared:linkReleaseStaticLinuxX64`
- Output: `/shared/build/bin/linuxX64/releaseStatic/libshared.a`

#### Wasm binary file

- Run `./gradlew :shared:wasmJsBrowserDistribution`
- Output: `/shared/build/dist/wasmJs/productionExecutable/shared-wasm-js.wasm`
