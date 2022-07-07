pipeline {
    agent any
    tools {
        maven 'mvn-386'
        jdk 'jdk-17'
    }
    stages {
        stage('Compile') {
            steps {
                withMaven() {
                    sh 'mvn compile'
                }
            }
        }
        stage('Test') {
            steps {
                withMaven() {
                    sh 'mvn verify'
                }
            }
        }
    }
}