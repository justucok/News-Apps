plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    kotlin("android") version "1.5.0"
}

//kotlin {
//    android()
//
//    // Gunakan JVM Toolchain untuk menetapkan versi Java
//    val jvmTarget: String by project
//    val toolchain = KotlinJvmToolchain(gradle, jvmTarget)
//
//    sourceSets {
//        named("main") {
//            kotlin.srcDir("src/main/kotlin")
//            java.srcDir("src/main/java")
//        }
//    }
//
//    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//        kotlinOptions.jvmTarget = jvmTarget
//        useToolchain(toolchain)
//    }
//}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

//    compileSdkVersion(34)
//
//    // ...

//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.squareup.retrofit2:retrofit2:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

}