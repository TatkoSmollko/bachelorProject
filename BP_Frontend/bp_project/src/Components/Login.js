import React,{useState} from "react"
import { Grid, Paper, Avatar, TextField, Button } from "@material-ui/core"
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import login from './Actions/Login.js';

const Login = (props) => {
    const paperStyle = { padding: 20, height: '70vh', width: 280, margin: '20px auto' }
    const avatarStyle = { backgroundColor: 'green' }
    const [userName, setUserName ] = useState('');
    const [password, setPassword ] = useState('');

   const handleSubmit = () => {
        login(userName, password,props);
    }

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <Avatar style={avatarStyle}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <h2>STU FEI</h2>
                </Grid>
                <TextField value={userName} onChange={e => setUserName(e.target.value)} label='Username' placeholder='Enter username' fullWidth required />
                <TextField value={password} onChange={e => setPassword(e.target.value)} label='Password' placeholder='Enter password' type='password' fullWidth required />
                <FormControlLabel
                    control={
                        <Checkbox
                            name="checkedB"
                            color="primary"
                        />
                    }
                    label="Remember me"
                />
                <Button type='submit' variant="contained" color="primary" fullWidth onClick= {handleSubmit}>Sign in</Button>

            </Paper>
        </Grid>
    )
}

export default Login;