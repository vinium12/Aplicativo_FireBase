plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Adicione o plugin do Google Services para o Firebase
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.appfirebase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.appfirebase"
        minSdk = 24
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // --- Dependências adicionadas do projeto antigo ---
    implementation(libs.androidx.navigation.compose) // Se você usa navegação Compose
    implementation(libs.firebase.auth) // Para autenticação Firebase
    implementation(libs.androidx.runtime.livedata) // Se você usa LiveData
    implementation(libs.androidx.credentials) // Para a API de Credenciais
    implementation(libs.androidx.credentials.play.services.auth) // Para integração com Google Play Services Auth
    implementation(libs.googleid) // Pode ser necessário para autenticação com Google
    // --- Fim das dependências adicionadas ---

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}