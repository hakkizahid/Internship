import React from "react";
import axios from 'axios';
import {Link} from 'react-router-dom';
import "../pagesCss/Movie.css";
import { Route, NavLink, Switch } from "react-router-dom";

class Movies extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            id:"",
            movies:[]
        };
      }

    componentDidMount(){
        axios.get("http://localhost:8080/api/movies")
        .then(res => {
            this.setState({movies: res.data})        
        })
    }

    clickHandlerDelete = () => {
        // var obj = {id: this.state.id};
        // axios.delete("http://localhost:8080/api/movies", obj).then(res => {
        //   console.log("Log in successful");
        //   this.props.history.push("/main");
        // });
      };

    TableCreate = () => {
        const table = [];
        for (let index = 0; index < this.state.movies.length; index++) {
                //this.setState({id: this.state.movies[index].id});
                table.push(                    
                    <tr>
                        <td>{this.state.movies[index].id}</td>
                        <td>{this.state.movies[index].name}</td>
                        <td>{this.state.movies[index].releaseDate}</td>
                        <td>{this.state.movies[index].imdbRating}</td>
                        <td>{this.state.movies[index].duration}</td>
                        <td>{this.state.movies[index].genre}</td>
                    </tr> 
                );
        }
        return table;
    }

    clickHandler = () => {
          this.props.history.push("/main");
      };

    render(){
        return(
            <div className = "outerDiv3">
                <span className ="moviesTitle" style ={{color: "aliceblue"}}>MOVIES</span>
                <table className = "moviesTable">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Release Date</th>
                        <th>IMDB Rate</th>
                        <th>Duration</th>
                        <th>Genre</th>
                    </tr>
                    <this.TableCreate/>
                </table>
                <br/>
                <Link className = "addlinkerMovies" to="/addmovie">ADD MOVIE</Link>
                <br/>
                <br/>
                <Link className = "addlinkerMovies" to="/searchmovie">SEARCH MOVIE</Link>
                <br/>
                <br/>
                <Link className = "linkerMovies" to="/main">Return to Main Page</Link>
            </div>
        );
    }
}

export default Movies;