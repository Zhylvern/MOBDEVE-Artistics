# Artistics Android Application

## Overview
This Android app serves as a social media platform designed exclusively for music enthusiasts. Users can upload, share, and discover music, creating a vibrant community centered around their passion for tunes.

## Installation
Steps to install and set up the app:
1. Clone the repository: `git clone https://https://github.com/Zhylvern/MOBDEVE-Artistics/`
2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device

# Supabase Backend

This is a backend server built with **Node.js**, **Express**, and **Supabase**. 

## Prerequisites

Before running the server, ensure you have the following tools installed:

- **Node.js** (v14 or later)
- **npm** (Node Package Manager)
- **Supabase Project** (sign up at [Supabase.io](https://supabase.io) and create a project)
- **TypeScript** and **ts-node** (for TypeScript development)

## Setting Up the Project

Follow these steps to set up and run the backend server:

### 1. Install Dependencies

In the project root directory, install the required npm packages:

npm install

This will install all the necessary dependencies, including express, dotenv, supabase-js, and TypeScript.

### 2. Create a .env File

In the project root directory, create a .env file with the following environment variables:

`SUPABASE_URL=<your-supabase-url>`

`SUPABASE_KEY=<your-supabase-api-key>`

    SUPABASE_URL: This is the URL of your Supabase project (you can find this in the Supabase dashboard).
    SUPABASE_KEY: This is the key (anon key or service role key) used to authenticate requests to Supabase.

### 3. Run the Server

To run the server using ts-node directly without compiling TypeScript first, use the following command:

```npx ts-node src/index.ts```

This will start the Express server, and you should see the following message in your terminal:

Server is running on port 3000

The server will be accessible at http://localhost:3000.
