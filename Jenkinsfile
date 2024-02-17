pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "nyamatulla/springboot-app:${BUILD_NUMBER}"
        DOCKER_CONT = "springboot-blog${BUILD_NUMBER}"
        REGISTRY_CREDENTIALS = credentials('dockerHub')
    }
    
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
        /* stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                  sh "mvn clean verify sonar:sonar -Dsonar.projectKey=springboot -Dsonar.projectName='springboot'"
                }
            }
        } */
        stage('Docker build and Push') {
            steps {
               script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                    def dockerImage = docker.image("${DOCKER_IMAGE}")
                    docker.withRegistry('https://index.docker.io/v1/', "dockerHub") {
                        dockerImage.push()
                    } 
               } 
            }
        }  
    }
}
    