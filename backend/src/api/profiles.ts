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
  const { id } = req.body;

  // Check if user id exists
  if (!id) {
    return res.status(400).json({ error: 'No user id' });
  }

  const { data, error } = await supabase
    .from('profiles')
    .select('*')
    .eq("user_id", id)
  if (error) {
    return res.status(500).json({ error: error.message });
  }
  res.status(200).json(data);
})

// User Profile Update
router.put('/user/update', async (req: any, res: any) => {
  const { id, username, name, bio } = req.body;

  // Check if user id exists
  if (!id) {
    return res.status(400).json({ error: 'User  id is required' });
  }

  // Create an object for the fields to be updated
  const updates: any = {};
  if (username) updates.username = username;
  if (name) updates.name = name;
  if (bio) updates.bio = bio;

  // Check if there are any fields to update
  if (Object.keys(updates).length === 0) {
    return res.status(400).json({ error: 'At least one field (username, name, bio) must be provided for update' });
  }

  const { data, error } = await supabase
    .from('profiles')
    .update(updates)
    .eq("user_id", id);

  if (error) {
    return res.status(500).json({ error: error.message });
  }

  res.status(200).json({ message: 'User  profile updated successfully', data });
});

export default router;
