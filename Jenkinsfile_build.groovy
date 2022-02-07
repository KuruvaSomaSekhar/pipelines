pipeline{
    agent any
    stages{
        stage("helloworld"){
            steps{
                println "Welcome to Jenkins pipeline world"
            }
        }
        stage("build"){
            steps {
                print "Here I'm building artifacts file"
                sh """
                mvn clean package
                whoami
                pwd
                ls -l                
                """
            }
        }
        stage("upload"){
            steps {
                println "Here We will update artifacts to S3 buckets"
                sh "aws s3 cp target/hello-*.war s3://devops09art"
            }
        }
        stage("deploy"){
            steps {
                println "Here we deploy artifacts to any environment"
            }
        }
    }
}