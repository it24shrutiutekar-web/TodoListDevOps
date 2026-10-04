pipeline {

    agent any

    environment {
        MAVEN_HOME = 'C:\\Users\\Shruti\\Downloads\\apache-maven-3.9.11-bin\\apache-maven-3.9.11'
        DOCKER_IMAGE = 'utekar/todo-list-app:v1'
    }

    stages {

        stage('Check Tools') {
            steps {
                bat 'java -version'
                bat 'call "%MAVEN_HOME%\\bin\\mvn.cmd" -version'
                bat 'docker --version'
            }
        }

        stage('Maven Build') {
            steps {
                bat 'call "%MAVEN_HOME%\\bin\\mvn.cmd" clean package -DskipTests'
            }
        }

        stage('Selenium Test') {
            steps {
                bat 'call "%MAVEN_HOME%\\bin\\mvn.cmd" test'
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