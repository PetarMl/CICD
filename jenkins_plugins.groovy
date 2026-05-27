import jenkins.model.Jenkins

pm=Jenkins.instance.pluginManager
uc=Jenkins.instance.UpdateCenter

uc.updateAllSites()
["github","docker-workflow","prometheus","ansible","maven"].each{
    if(![pm.getPlugin(it)]){
        deployment=uc.getPlugin(it).deploy(true)
        deployment.get()
    }
}
Jenkins.instance.restart()