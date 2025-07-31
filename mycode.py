import os
import sqlite3

class UserManager:
    def __init__(self, db_path):
        self.db_path = db_path
        self.conn = sqlite3.connect(self.db_path) 
    def create_user(self, username, password):
        
        query = f"INSERT INTO users (username, password) VALUES ('{username}', '{password}')"
        self.conn.execute(query)
        self.conn.commit()

    def authenticate(self, username, password):
        
        query = f"SELECT * FROM users WHERE username = '{username}' AND password = '{password}'"
        cursor = self.conn.execute(query)
        return cursor.fetchone() is not None

    def delete_user(self, username):
        
        query = f"DELETE FROM users WHERE username = '{username}'"
        self.conn.execute(query)
        self.conn.commit()

    def get_user_info(self, username):
        
        query = f"SELECT * FROM users WHERE username = '{username}'"
        cursor = self.conn.execute(query)
        return cursor.fetchone()

    def __del__(self):
        
        self.conn.close()

manager = UserManager(None)
manager.create_user("admin", "admin123")

