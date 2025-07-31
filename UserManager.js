// UserManager.js

const fs = require('fs').promises;
const path = require('path');

class UserManager {
  constructor(filePath = 'users.json') {
    this.users = [];
    this.filePath = path.resolve(filePath);
  }

  addUser(user) {
    if (!user || !user.name) {
      throw new Error('Invalid user object');
    }

    this.users.push(user);
    console.log(`User added: ${user.name}`);
  }

  removeUser(userName) {
    const index = this.users.findIndex(user => user.name === userName);
    if (index !== -1) {
      this.users.splice(index, 1);
      console.log(`User removed: ${userName}`);
    } else {
      console.warn(`User not found: ${userName}`);
    }
  }

  getUserDetails(userName) {
    return this.users.find(user => user.name === userName) || null;
  }

  async saveUsersToFile() {
    try {
      const data = JSON.stringify(this.users, null, 2);
      await fs.writeFile(this.filePath, data, 'utf-8');
      console.log('Users saved to file.');
    } catch (error) {
      console.error('Error saving users:', error.message);
    }
  }

  async loadUsersFromFile() {
    try {
      const data = await fs.readFile(this.filePath, 'utf-8');
      this.users = JSON.parse(data);
      console.log('Users loaded from file.');
    } catch (error) {
      if (error.code === 'ENOENT') {
        console.warn('User file not found. Starting with an empty list.');
        this.users = [];
      } else {
        console.error('Error loading users:', error.message);
      }
    }
  }
}

module.exports = UserManager;

