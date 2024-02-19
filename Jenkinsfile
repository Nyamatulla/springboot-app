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
                sh 'mvn clean install'
            }
        }
        /* stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                  sh "mvn clean verify sonar:sonar -Dsonar.projectKey=springboot -Dsonar.projectName='springboot'"
                }
            }
        } */

        stage('Deploy to Nexus') {
            steps {
                script {
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: 'http://localhost:8081/',
                        groupId: 'com.example',
                        version: '1.0-SNAPSHOT',
                        repository: 'maven-snapshots',
                        credentialsId: 'nexus-cred',
                        artifacts: [
                            [artifactId: 'devops-blog', classifier: '', file: 'target/devops-blog-1.0-SNAPSHOT.jar', type: 'jar']
                        ]
                    )
                }
            }
        }
       /* stage('Docker build and Push') {
            steps {
               script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                    def dockerImage = docker.image("${DOCKER_IMAGE}")
                    docker.withRegistry('https://index.docker.io/v1/', "dockerHub") {
                        dockerImage.push()
                    } 
               } 
            }
        } */  
    }
}
    
