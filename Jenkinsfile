pipeline {
    agent { label 'docker' }

    tools {
        jdk 'jdk21'
        maven 'maven'
    }

    environment {
        SCANNER_HOME = tool 'sonar-scanner'

        DOCKER_IMAGE = 'rahulguthi/my-sonarqube-project'
        IMAGE_TAG = "${BUILD_NUMBER}"
        LATEST_TAG = "latest"
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout Source') {
            steps {
                echo "Checking out source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building application..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running unit tests..."
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh """
                        ${SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectKey=my-sonarqube-project \
                        -Dsonar.projectName=my-sonarqube-project \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.java.binaries=target/classes
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh """
                    docker build \
                    -t ${DOCKER_IMAGE}:${IMAGE_TAG} \
                    -t ${DOCKER_IMAGE}:${LATEST_TAG} .
                """
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'dockerhub') {
                        sh """
                            docker push ${DOCKER_IMAGE}:${IMAGE_TAG}
                            docker push ${DOCKER_IMAGE}:${LATEST_TAG}
                        """
                    }
                }
            }
        }

        stage('Deploy Container') {
            steps {
                echo "Deploying container..."

                sh '''
                    docker stop my-sonarqube-project || true
                    docker rm my-sonarqube-project || true

                    docker run -d \
                      --name my-sonarqube-project \
                      -p 8081:8081 \
                      --restart unless-stopped \
                      rahulguthi/my-sonarqube-project:latest
                '''
            }
        }
    }

    post {

        success {
            echo "======================================"
            echo "Pipeline completed successfully."
            echo "Docker Image: ${DOCKER_IMAGE}:${LATEST_TAG}"
pipeline {
    agent { label 'docker' }

    tools {
        jdk 'jdk21'
        maven 'maven3'
    }

    environment {
        SCANNER_HOME = tool 'sonar-scanner'

        DOCKER_IMAGE = 'rahulguthi/my-sonarqube-project'
        IMAGE_TAG = "${BUILD_NUMBER}"
        LATEST_TAG = "latest"
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout Source') {
            steps {
                echo "Checking out source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building application..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running unit tests..."
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
        stage('Push Docker Image') {
            steps {
                    docker.withRegistry('', 'dockerhub') {
                            docker push ${DOCKER_IMAGE}:${IMAGE_TAG}

        }

            echo "======================================"
            echo "======================================"
        }
        always {
        }
    }
}            cleanWs()

            echo "Pipeline failed."
            echo "Check the build logs."
        failure {
            echo "Pipeline completed successfully."
            echo "Docker Image: ${DOCKER_IMAGE}:${LATEST_TAG}"
            echo "======================================"
        success {
            echo "======================================"

    post {
            }
        }
    }
                      --restart unless-stopped \
                      rahulguthi/my-sonarqube-project:latest
                '''
                            docker push ${DOCKER_IMAGE}:${LATEST_TAG}
                        """
                      --name my-sonarqube-project \
                      -p 8081:8081 \
                    docker rm my-sonarqube-project || true

                    docker run -d \
                    }
                }
                sh '''
                    docker stop my-sonarqube-project || true
            }
        }

        stage('Deploy Container') {
            steps {
                echo "Deploying container..."

                        sh """
                script {
                withSonarQubeEnv('SonarQube') {
        }

                    sh """
                        ${SCANNER_HOME}/bin/sonar-scanner \
            }
                    -t ${DOCKER_IMAGE}:${IMAGE_TAG} \
                    -t ${DOCKER_IMAGE}:${LATEST_TAG} .
                """

                    docker build \
        stage('Build Docker Image') {
                sh """
            steps {
                echo "Building Docker image..."
                        -Dsonar.projectKey=my-sonarqube-project \
        }

            }
                    waitForQualityGate abortPipeline: true
                }
                timeout(time: 5, unit: 'MINUTES') {
            steps {
        stage('Quality Gate') {
                        -Dsonar.projectName=my-sonarqube-project \
        }
            }
                        -Dsonar.sources=src/main/java \
