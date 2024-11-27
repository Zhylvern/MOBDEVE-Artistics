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
router.post('/', upload.fields([{ name: 'image' }, { name: 'song' }]), async (req: any, res: any) => {
  const { caption } = req.body;
  const imageFile = req.files?.image ? req.files.image[0] : null;
  const songFile = req.files?.song ? req.files.song[0] : null;

  // Check if at least one file was uploaded (image or song)
  if (!imageFile && !songFile) {
    console.log("No files uploaded");
    return res.status(400).json({ error: 'No files uploaded' });
  }

  // Initialize URLs for image and song
  let imageURL = null;
  let songURL = null;

  // Upload image to Supabase Storage (if image was uploaded)
  if (imageFile) {
    const { error: uploadError } = await supabase.storage
      .from('post') // Replace with your bucket name
      .upload(`img/${imageFile.originalname}`, imageFile.buffer, {
        contentType: imageFile.mimetype,
        upsert: true, // Overwrite if file already exists
      });

    // Handle upload error for image
    if (uploadError) {
      console.error("Image Upload Error:", uploadError); // Log the error for debugging
      return res.status(500).json({
        error: "Image upload failed",
        message: uploadError.message,
        code: "IMAGE_UPLOAD_ERROR", // Custom error code
      });
    }

    // Get the public URL of the uploaded image
    const { data: publicImageURLData } = supabase.storage
      .from('post') // Replace with your bucket name
      .getPublicUrl(`img/${imageFile.originalname}`);

    imageURL = publicImageURLData?.publicUrl;

    if (!imageURL) {
      console.log("Failed to retrieve image public URL");
      return res.status(500).json({
        error: "Failed to retrieve image public URL",
        code: "IMAGE_URL_ERROR"
      });
    }
  }

  // Upload song to Supabase Storage (if song was uploaded)
  if (songFile) {
    const { error: uploadError } = await supabase.storage
      .from('post') // Replace with your bucket name
      .upload(`song/${songFile.originalname}`, songFile.buffer, {
        contentType: songFile.mimetype,
        upsert: true, // Overwrite if file already exists
      });

    // Handle upload error for song
    if (uploadError) {
      console.error("Song Upload Error:", uploadError); // Log the error for debugging
      return res.status(500).json({
        error: "Song upload failed",
        message: uploadError.message,
        code: "SONG_UPLOAD_ERROR", // Custom error code
      });
    }

    // Get the public URL of the uploaded song
    const { data: publicSongURLData } = supabase.storage
      .from('post') // Replace with your bucket name
      .getPublicUrl(`song/${songFile.originalname}`);

    songURL = publicSongURLData?.publicUrl;

    if (!songURL) {
      console.log("Failed to retrieve song public URL");
      return res.status(500).json({
        error: "Failed to retrieve song public URL",
        code: "SONG_URL_ERROR"
      });
    }
  }

  // Insert post with the caption, image URL, and song URL
  const { data: postData, error: postError } = await supabase.from('posts').insert([
    { caption, img_url: imageURL, song_url: songURL },
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
  console.log("Post created successfully", postData);
  res.status(201).json(postData);
});

export default router;
