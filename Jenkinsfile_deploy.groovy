//declarative pipeline
pipeline {
    agent any
    parameters {
        // Define string parameter.
        string (
            name: 'jobName',
            defaultValue: '',
            description: '{Provide job name}'
        )
        string (
            name: 'serverIP',
            defaultValue: '',
            description: '{Provide job name}'
        )

        string (
            name: 'buildNumber',
            defaultValue: '',
            description: '{Provide build number}'
        )
         string (
            name: 'sourceBranch',
            defaultValue: '',
            description: '{Provide your source code branch}'
        )

    }
    stages{
        stage("deploy"){
            steps {
                println "Here I'm adding deployment steps"
                sh """aws s3 cp s3://devops09art/${jobName}/${sourceBranch}/${buildNumber}/hello-${buildNumber}.war .
                ls -la
                ls -la /tmp/
                scp -oStrictHostKeyChecking=no -i /tmp/devops09.pem hello-${buildNumber}.war ec2-user@${serverIP}:/var/lib/tomcat/webapps
                """
            }
        }
    }
}