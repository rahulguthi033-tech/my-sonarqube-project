pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'jdk21'
    }

    environment {
        SCANNER_HOME = tool 'sonar-scanner'
        DOCKERHUB_USERNAME = 'rahulguthi'
        DOCKER_IMAGE = "${DOCKERHUB_USERNAME}/sonarqube-jenkins-pipeline:latest"
    }

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rahulguthi033-tech/SonarQube-Jenkins-Pipeline.git'
            }
        }

        stage('Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('Sonar Analysis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh "$SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=SonarQube-Jenkins-Pipeline -Dsonar.projectKey=SonarQube-Jenkins-Pipeline -Dsonar.java.binaries=target/classes"
                }
            }
        }

        stage('Build') {
            steps {
                sh "mvn package"
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t $DOCKER_IMAGE ."
                }
            }
        }

        stage('Docker Push to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKERHUB_USERNAME',
                        passwordVariable: 'DOCKERHUB_PASSWORD'
                    )]) {
                        echo "Docker Hub Username: ${DOCKERHUB_USERNAME}"
                        echo "Docker Image: ${DOCKER_IMAGE}"

                        sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                        sh "docker push $DOCKER_IMAGE"
                    }
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    sh "docker stop sonarqube-jenkins-pipeline || true && docker rm sonarqube-jenkins-pipeline || true"

                    sh "docker run -d --name sonarqube-jenkins-pipeline -p 5555:5555 $DOCKER_IMAGE"
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
