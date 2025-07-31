// badClass.js

const fs = require('fs')
var path = require('path') // inconsistent declaration

class usermanager{
    constructor(){
        this.Users=[]
    }

    adduser(user){
        this.Users.push(user)
        console.log("user added: "+user.name)
    }

    removeUser(userName){
        for(let i=0;i<this.Users.length;i++){
            if(this.Users[i].name==userName){
                this.Users.splice(i,1)
                console.log("Removed "+userName)
                break
            }
        }
    }

    getuserdetails(username){
        for(let i=0;i<this.Users.length;i++){
            if(this.Users[i].name===username){
                return this.Users[i]
            }
        }
        return null
    }

    saveUsersToFile(){
        fs.writeFileSync('users.json',JSON.stringify(this.Users)) // no error handling
        console.log("Saved")
    }

    loadusersfromfile(){
        let data = fs.readFileSync('users.json') // no try-catch
        this.Users = JSON.parse(data)
    }
}

module.exports=usermanager

