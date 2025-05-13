const express = require('express');
const path = require('path');
const morgan = require('morgan');
const helmet = require('helmet');
const compression = require('compression');
const cors = require('cors');
require('dotenv').config();

const app = express();
const port = process.env.PORT || 3000;
const db = require('./config/database');

// Middleware
app.use(morgan('dev')); // Logging
app.use(helmet()); // Security headers
app.use(compression()); // Compress responses
app.use(cors()); // Enable CORS
app.use(express.json()); // Parse JSON bodies
app.use(express.urlencoded({ extended: true })); // Parse URL-encoded bodies

// View engine setup
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Static files
app.use(express.static(path.join(__dirname, 'public')));

// Database test route
app.get('/db-test', async (req, res) => {
  try {
    // Test query
    const result = await db.query('SELECT NOW()');
    res.json({
      status: 'success',
      message: 'Database connected successfully',
      timestamp: result.rows[0].now,
      database: {
        host: process.env.POSTGRES_HOST,
        port: process.env.POSTGRES_PORT,
        database: process.env.POSTGRES_DB,
        user: process.env.POSTGRES_USER
      }
    });
  } catch (error) {
    console.error('Database connection error:', error);
    res.status(500).json({
      status: 'error',
      message: 'Database connection failed',
      error: error.message,
      database: {
        host: process.env.POSTGRES_HOST,
        port: process.env.POSTGRES_PORT,
        database: process.env.POSTGRES_DB,
        user: process.env.POSTGRES_USER
      }
    });
  }
});

// Routes
app.get('/', async (req, res) => {
  try {
    // Test database connection
    const dbStatus = await db.query('SELECT NOW()');
    res.render('index', { 
      title: 'Home',
      dbConnected: true,
      dbTimestamp: dbStatus.rows[0].now
    });
  } catch (error) {
    res.render('index', { 
      title: 'Home',
      dbConnected: false,
      error: error.message
    });
  }
});

// Error handling middleware
app.use((req, res, next) => {
  const error = new Error('Not Found');
  error.status = 404;
  next(error);
});

app.use((error, req, res, next) => {
  res.status(error.status || 500);
  res.render('error', {
    message: error.message,
    error: process.env.NODE_ENV === 'development' ? error : {}
  });
});

// Start server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
}); 