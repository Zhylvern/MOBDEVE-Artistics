import express from 'express';
import bodyParser from 'body-parser';
import postsRouter from './api/posts';

const app = express();
app.use(bodyParser.json());

app.use('/posts', postsRouter);

const port = 3000;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});

// npx ts-node src/index.ts