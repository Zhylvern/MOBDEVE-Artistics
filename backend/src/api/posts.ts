import { createClient } from '@supabase/supabase-js';
import express from 'express';
import dotenv from 'dotenv';
import multer from 'multer';

dotenv.config();

const router = express.Router();
const supabaseUrl = process.env.SUPABASE_URL as string;
const supabaseKey = process.env.SUPABASE_KEY as string;
const supabase = createClient(supabaseUrl, supabaseKey);

// Set up multer for file uploads
const storage = multer.memoryStorage();
const upload = multer({ storage });

router.get('/', async (req: any, res: any) => {
  const { data, error } = await supabase.from('posts').select('*');
  if (error) {
    return res.status(500).json({ error: error.message });
  }
  res.status(200).json(data);
});

// Handle file upload and post creation
router.post('/', upload.single('image'), async (req: any, res: any) => {
  const { caption } = req.body;
  const file = req.file;

  // Check if a file was uploaded
  if (!file) {
    return res.status(400).json({ error: 'No file uploaded' });
  }

  // Upload file to Supabase Storage
  const { error: uploadError } = await supabase.storage
    .from('post') // Replace with your bucket name
    .upload(`img/${file.originalname}`, file.buffer, {
      contentType: file.mimetype,
      upsert: true, // Overwrite if file already exists
    });

  // Handle upload error
  if (uploadError) {
    console.error("Upload Error:", uploadError); // Log the error for debugging
    return res.status(500).json({
      error: "Upload failed",
      message: uploadError.message,
      code: "UPLOAD_ERROR" // Custom error code
    });
  }

  // Get the public URL of the uploaded file
  const { data: publicURL } = supabase.storage
    .from('post') // Replace with your bucket name
    .getPublicUrl(`img/${file.originalname}`);

  // Insert post with the caption and image URL
  const { data: postData, error: postError } = await supabase.from('posts').insert([
    { caption, img_url: publicURL },
  ]);

  // Handle post creation error
  if (postError) {
    console.error("Post Creation Error:", postError); // Log the error for debugging
    return res.status(500).json({
      error: "Post creation failed",
      message: postError.message,
      code: "POST_CREATION_ERROR" // Custom error code
    });
  }

  // Respond with the created post data
  res.status(201).json(postData);
});

export default router;
