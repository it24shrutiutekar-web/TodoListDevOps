pipeline {

    agent any

    environment {
        DOCKER_IMAGE = 'utekar/todo-list-app:v1'
    }

    stages {

        stage('Maven Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Selenium Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {

                    bat 'echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin'

                    bat 'docker push %DOCKER_IMAGE%'
                }
            }
        }
    }

    post {
        success {
            echo 'DevOps Pipeline Completed Successfully!'
        }

        failure {
            echo 'DevOps Pipeline Failed!'
        }
    }
}