import { createClient } from '@supabase/supabase-js';
import express from 'express';
import dotenv from 'dotenv';

dotenv.config();

const router = express.Router();
const supabaseUrl = process.env.SUPABASE_URL as string;
const supabaseKey = process.env.SUPABASE_KEY as string;
const supabase = createClient(supabaseUrl, supabaseKey);

// All Profiles
router.get('/users', async (res: any) => {
  const { data, error } = await supabase.from('profiles').select('*');
  if (error) {
    return res.status(500).json({ error: error.message });
  }
  res.status(200).json(data);
});

// User Profile
router.get('/user', async (req: any, res: any) => {
  const { userId } = req.query;

  // Log incoming request details
  console.log('Incoming request to /user endpoint');
  console.log('Query parameters:', req.query);

  // Check if user id exists
  if (!userId) {
    console.error('No user id provided');
    return res.status(400).json({ error: 'No user id' });
  }

  console.log('User  ID received:', userId);

  const { data, error } = await supabase
    .from('profiles')
    .select('*')
    .eq("user_id", userId);

  // Log the result of the database query
  if (error) {
    console.error('Database error:', error.message);
    return res.status(500).json({ error: error.message });
  }

  console.log('Data retrieved from database:', data);

  res.status(200).json(data);
});

// User Profile Update
router.put('/user/update', async (req: any, res: any) => {
  const { id, username, name, bio } = req.body;

  console.log('Received request to update user profile:', { id, username, name, bio });

  // Check if user id exists
  if (!id) {
    console.error('User  ID is required');
    return res.status(400).json({ error: 'User  id is required' });
  }

  // Create an object for the fields to be updated
  const updates: any = {};
  if (username) {
    updates.username = username;
    console.log('Username to update:', username);
  }
  if (name) {
    updates.name = name;
    console.log('Name to update:', name);
  }
  if (bio) {
    updates.bio = bio;
    console.log('Bio to update:', bio);
  }

  // Check if there are any fields to update
  if (Object.keys(updates).length === 0) {
    console.warn('No fields provided for update');
    return res.status(400).json({ error: 'At least one field (username, name, bio) must be provided for update' });
  }

  console.log('Updating user profile with the following fields:', updates);

  const { data, error } = await supabase
    .from('profiles')
    .update(updates)
    .eq("user_id", id);

  if (error) {
    console.error('Error updating profile:', error.message);
    return res.status(500).json({ error: error.message });
  }

  console.log('User  profile updated successfully:', data);
  res.status(200).json({ message: 'User  profile updated successfully', data });
});

export default router;
