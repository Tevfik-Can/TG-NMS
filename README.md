# TG-NMS Website

A modern Node.js website template built with Express.js and EJS templating engine.

## Features

- Express.js web framework
- EJS templating engine
- Modern UI with Bootstrap 5
- Security middleware (Helmet)
- Request logging (Morgan)
- CORS enabled
- Compression middleware
- Error handling
- Development hot-reload
- Environment variables support

## Prerequisites

- Node.js (v14 or higher)
- npm or yarn

## Installation

1. Clone the repository:
```bash
git clone <your-repo-url>
cd tg-nms
```

2. Install dependencies:
```bash
npm install
# or
yarn install
```

3. Create a `.env` file in the root directory:
```bash
PORT=3000
NODE_ENV=development
```

## Development

To start the development server with hot-reload:

```bash
npm run dev
# or
yarn dev
```

The server will start at `http://localhost:3000`

## Production

To start the production server:

```bash
npm start
# or
yarn start
```

## Project Structure

```
.
├── src/
│   ├── app.js          # Application entry point
│   ├── public/         # Static files
│   │   ├── css/       # Stylesheets
│   │   └── js/        # Client-side JavaScript
│   └── views/         # EJS templates
├── .env               # Environment variables
├── .gitignore        # Git ignore rules
├── package.json      # Project metadata and dependencies
└── README.md         # Project documentation
```

## License

This project is licensed under the MIT License. 