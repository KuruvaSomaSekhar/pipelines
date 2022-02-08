//declarative pipelines
pipeline {
    agent any
    stages {
        stage("clone"){
            steps {
                println "Here we download source code from the private repo"
            }
        }
        stage("build"){
            steps{
                println "Here we build our code"
            }
        }
        stage("artifacts-upload"){
            steps{
                println "upload artifacts to s3"
            }
        }
    }
}