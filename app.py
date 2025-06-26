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
    items = []

    # Sidepanel Icons are from https://fontawesome.com/v5/search
    # The Link name lengths have to be consistent otherwise they misalign
    PanelLinks = {
        "Main Menu": ["/","fa-home"],
        "Extra aa" : ["#", "fa-chart-pie"],
        "Extraa a" : ["#", "fa-chart-pie"],
        "Extrraaa" : ["#", "fa-brain"],
        "Extraaaa" : ["#", "fa-brain"],
        "Testttta" : ["#", "fa-brain"],
        "Test2 aa" : ["#", "fa-brain"]
                        }
    return render_template('index.html', items=items, PanelLinks = PanelLinks, curr_page = request.path)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=3000, debug=True)