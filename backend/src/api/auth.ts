import { createClient } from '@supabase/supabase-js';
import express from 'express';
import dotenv from 'dotenv';

dotenv.config();

const router = express.Router();

const supabaseUrl = process.env.SUPABASE_URL as string;
const supabaseKey = process.env.SUPABASE_KEY as string;
const supabase = createClient(supabaseUrl, supabaseKey);

/**
 * Route: /register
 * @desc Registers a new user in the Supabase Auth system.
 * @params:
 *    - email: string, the user's email address.
 *    - password: string, the user's password.
 * @requestBody:
 *    - { email: <string>, password: <string> }
 * @response:
 *    - Success: 201, { message: 'User  registered successfully', user: <user data> }
 *    - Failure: 400, { message: <error message> }
 */
router.post('/register', async (req: any, res: any) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).json({ message: 'Email and password are required' });
  }

  const { data, error } = await supabase.auth.signUp({ email, password });

  if (error) {
    console.error('Registration error:', error); // Log the error to the console
    return res.status(400).json({ message: error.message });
  }

  res.status(201).json({
    message: 'User  registered successfully',
    user: data.user
  });
});

/**
 * Route: /login
 * @desc Logs in an existing user and returns an access token.
 * @params:
 *    - email: string, the user's email address.
 *    - password: string, the user's password.
 * @requestBody:
 *    - { email: <string>, password: <string> }
 * @response:
 *    - Success: 200, { message: 'Login successful', user: <user data>, accessToken: <JWT token> }
 *    - Failure: 400, { message: <error message> }
 */
router.post('/login', async (req: any, res: any) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).json({ message: 'Email and password are required' });
  }

  const { data, error } = await supabase.auth.signInWithPassword({ email, password });

  if (error) {
    console.error('Login error:', error); // Log the error to the console
    return res.status(400).json({ message: error.message });
  }

  if (!data?.session) {
    console.error('Login error: Session not found'); // Log session not found error
    return res.status(400).json({ message: 'Session not found' });
  }

  const { access_token } = data.session;

  res.status(200).json({
    message: 'Login successful',
    user: data.user,
    accessToken: access_token  // Corrected to `access_token` from the session
  });
});

/**
 * Route: /logout
 * @desc Logs out the user by invalidating the current session.
 * @params:
 *    - accessToken: string, the user's JWT access token (used to identify the session).
 * @requestBody:
 *    - { accessToken: <string> }
 * @response:
 *    - Success: 200, { message: 'User  signed out successfully' }
 *    - Failure: 400, { message: <error message> }
 */
router.post('/logout', async (req: any, res: any) => {
  const { accessToken } = req.body;

  if (!accessToken) {
    return res.status(400).json({ message: 'Access token is required' });
  }

  // Correct usage of signOut method
  const { error } = await supabase.auth.signOut();

  if (error) {
    console.error('Logout error:', error); // Log the error to the console
    return res.status(400).json({ message: error.message });
  }

  res.status(200).json({ message: 'User  signed out successfully' });
});

export default router;
