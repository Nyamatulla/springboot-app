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
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                  sh "mvn clean verify sonar:sonar -Dsonar.projectKey=springboot -Dsonar.projectName='springboot'"
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                script {
                    sh '''
                        mvn deploy:deploy-file -Durl=http://192.168.43.37:8081/repository/maven-snapshots/ \
                        -DrepositoryId=maven-snapshots -DgroupId=com.example -DartifactId=devops-blog \
                        -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=target/devops-blog-1.0-SNAPSHOT.jar
                    '''

                }
            }
        }
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
        stage(" trivy scans") {
            steps {
                script {
                    sh "trivy fs --severity HIGH,CRITICAL -f json -o fs_results.json ."
                    echo " Scanned current file system"
                    echo "scanning docker image"
                    sh "trivy image --severity HIGH,CRITICAL -f json -o img_results.json ${DOCKER_IMAGE}"
                    echo " scan completed"
                }
            }
        }
    }
}
    
