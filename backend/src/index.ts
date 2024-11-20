import express from 'express';
import bodyParser from 'body-parser';
import postsRouter from './api/posts';
import authRouter from './api/auth'

const app = express();
app.use(bodyParser.json());

app.use('/auth', authRouter);
app.use('/posts', postsRouter);

const port = 3000;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
