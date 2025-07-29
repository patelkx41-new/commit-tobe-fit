from flask import Flask, request
import pickle
app = Flask(__name__)

class User:
    def __init__(self, name):
        self.name = name

@app.route('/deserialize', methods=['POST'])
def deserialize():
    # ❌ Insecure deserialization
    data = request.data
    user = pickle.loads(data)
    return f"Hello, {user.name}"

