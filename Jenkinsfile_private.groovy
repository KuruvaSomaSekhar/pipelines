//declarative pipelines
pipeline {
    agent any
    parameters {
        // Define string parameter.
        string (
            name: 'codebranch',
            defaultValue: '*/dev',
            description: '{Provide your source code branch}'
        )

    }
    stages {
        stage("clone"){
            steps {
                println "Here we download source code from the private repo"
                checkout([$class: 'GitSCM', branches: [[name: ${codebranch}]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git-auth2', url: 'https://github.com/KuruvaSomaSekhar/privatecode.git']]])
                sh """ls -lart ./*
                    echo  ${env. JOB_NAME} 
                    echo ${codebranch}
                    echo ${BUILD_NUMBER}

                    
                    """
        
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
                sh "ls -lart"
                sh "aws s3 cp target/hello-*.war s3://devops09art"
            }
        }
    }
}