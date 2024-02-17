pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                checkout scm
            }

        }
        stage("compile") {
            steps {
                sh 'mvn compile'
            }
        }
        stage('SonarQube Analysis') {
           withSonarQubeEnv('sonar') {
             sh "mvn clean verify sonar:sonar -Dsonar.projectKey=springboot -Dsonar.projectName='springboot'"
           }
        }     
    }
}
