import { createClient } from '@supabase/supabase-js';
import express from 'express';
import dotenv from 'dotenv';

dotenv.config();

const router = express.Router();
const supabaseUrl = process.env.SUPABASE_URL as string;
const supabaseKey = process.env.SUPABASE_KEY as string;
const supabase = createClient(supabaseUrl, supabaseKey);

router.get('/', async (req: any, res: any) => {
  const { data, error } = await supabase.from('posts').select('*');
  if (error) {
    return res.status(500).json({ error: error.message });
  }
  res.status(200).json(data);
});

router.post('/', async (req: any, res: any) => {
  const { id, caption } = req.body;
  const { data, error } = await supabase.from('posts').insert([{ id, caption }]);
  if (error) {
    return res.status(500).json({ error: error.message });
  }
  res.status(201).json(data);
});

export default router;
