import { createClient } from '@supabase/supabase-js';
import express from 'express';
import dotenv from 'dotenv';

dotenv.config();

const router = express.Router();

const supabaseUrl = process.env.SUPABASE_URL as string;
const supabaseKey = process.env.SUPABASE_KEY as string;
const supabase = createClient(supabaseUrl, supabaseKey);

// Register a new user
router.post('/register', async (req: any, res: any) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).json({ message: 'Email and password are required' });
  }

  const { data, error } = await supabase.auth.signUp({ email, password });

  if (error) {
    return res.status(400).json({ message: error.message });
  }

  res.status(201).json({
    message: 'User registered successfully',
    user: data.user
  });
});

// Log in a user
router.post('/login', async (req: any, res: any) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).json({ message: 'Email and password are required' });
  }

  const { data, error } = await supabase.auth.signInWithPassword({ email, password });

  if (error) {
    return res.status(400).json({ message: error.message });
  }

  if (!data?.session) {
    return res.status(400).json({ message: 'Session not found' });
  }

  const { access_token } = data.session;

  res.status(200).json({
    message: 'Login successful',
    user: data.user,
    accessToken: access_token  // Corrected to `access_token` from the session
  });
});

// Log out a user
router.post('/logout', async (req: any, res: any) => {
  const { accessToken } = req.body;

  if (!accessToken) {
    return res.status(400).json({ message: 'Access token is required' });
  }

  // Correct usage of signOut method
  const { error } = await supabase.auth.signOut();

  if (error) {
    return res.status(400).json({ message: error.message });
  }

  res.status(200).json({ message: 'User signed out successfully' });
});

export default router;
