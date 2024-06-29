pipeline {
    agent any
    tools{
        maven 'maven_3.9.8'
    }
    stages {
        stage('Build maven project') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/linuxdiallo/devopstodo']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image') {
            steps{
                script{
                  sh 'docker build -t todo .'
                  sh 'docker tag todo:latest aboubacar/todo'
                }
            }
        }
        stage ('Push image to docker hub') {
            steps {
                script {
                   withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {

                        sh 'docker login -u aboubacar -p ${dockerhubpwd}'

                        sh 'docker push aboubacar/todo'
                    }

                }
            }
        }
    }
}
