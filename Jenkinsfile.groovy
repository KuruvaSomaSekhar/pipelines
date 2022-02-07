pipeline {
    agent any
    stages {

        stage("build"){
            steps {
                println "Here I will run build commands"
                println "mvn clean package"
            }
        }
        stage("deploy"){
            steps {
                println "Here I will deploy code"
            }
        }

    }

}