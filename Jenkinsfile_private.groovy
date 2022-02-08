//declarative pipelines
pipeline {
    agent any
    stages {
        stage("clone"){
            steps {
                println "Here we download source code from the private repo"
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git-auth2', url: 'https://github.com/KuruvaSomaSekhar/privatecode.git']]])
                sh "ls -lart ./*"
        
            }
        }
        stage("build"){
            steps{
                println "Here we build our code"
                sh "mvn clean package"
            }
        }
        stage("artifacts-upload"){
            steps{
                println "upload artifacts to s3"
            }
        }
    }
}