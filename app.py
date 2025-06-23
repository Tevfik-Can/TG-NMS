from flask import Flask, render_template, request, redirect, url_for
import psycopg2
import os

app = Flask(__name__)

def get_db_connection():
    conn = psycopg2.connect(
        host=os.environ.get('POSTGRES_HOST', 'postgres'),
        port=os.environ.get('POSTGRES_PORT', 5432),
        dbname=os.environ.get('POSTGRES_DB', 'postgres'),
        user=os.environ.get('POSTGRES_USER', 'postgres'),
        password=os.environ.get('POSTGRES_PASSWORD', 'postgres')
    )
    return conn

@app.route('/', methods=['GET', 'POST'])
def index():
    # conn = get_db_connection()
    # cur = conn.cursor()
    # if request.method == 'POST':
    #     name = request.form['name']
    #     cur.execute("INSERT INTO test_items (name) VALUES (%s)", (name,))
    #     conn.commit()
    # cur.execute("CREATE TABLE IF NOT EXISTS test_items (id SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
    # cur.execute("SELECT id, name, created_at FROM test_items ORDER BY created_at DESC")
    # items = cur.fetchall()
    # cur.close()
    # conn.close()
    return render_template('index.html', items=items)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=3000)