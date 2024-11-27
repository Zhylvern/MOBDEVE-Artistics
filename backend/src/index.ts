import express from 'express';
import bodyParser from 'body-parser';
import postsRouter from './api/posts';
import authRouter from './api/auth';
import profileRouter from './api/profiles';

const app = express();
app.use(bodyParser.json());

app.use('/auth', authRouter);
app.use('/posts', postsRouter);
app.use('/profiles', profileRouter);

const port = 3000;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
