import React from 'react';
import { BrowserRouter, Route} from 'react-router-dom';
import LogIn from './pages/LogIn'
import Movies from './pages/Movies'
import Directors from './pages/Directors'
import Users from './pages/Users'
import MovieList from './pages/MovieList'
import MainPage from './pages/MainPage'
import AddMovie from './pages/AddMovie'
import AddUser from './pages/AddUser'
import AddDirector from './pages/AddDirector'
import SearchMovie from './pages/SearchMovie'
import SearchDirector from './pages/SearchDirector'
import SearchUser from './pages/SearchUser'

const AppRouter = () => (
    <BrowserRouter>
        <Route exact path="/movies" component = {Movies}/>
        <Route exact path="/directors" component = {Directors}/>
        <Route exact path="/users" component = {Users}/>
        <Route exact path="/movielist" component = {MovieList}/>
        <Route exact path="/main" component = {MainPage}/>
        <Route exact path="/addmovie" component = {AddMovie}/>
        <Route exact path="/adduser" component = {AddUser}/>
        <Route exact path="/adddirector" component = {AddDirector}/>
        <Route exact path="/searchmovie" component = {SearchMovie}/>
        <Route exact path="/searchdirector" component = {SearchDirector}/>
        <Route exact path="/searchuser" component = {SearchUser}/>
        <Route exact path="/" component = {LogIn}/>
    </BrowserRouter>
);

export default AppRouter;