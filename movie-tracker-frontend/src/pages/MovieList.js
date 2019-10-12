import React from "react";
import axios from 'axios';
import "../pagesCss/MovieList.css";
import {Link} from 'react-router-dom';

class MovieList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            username:"",
            type:"",
            movieList:[]
        };
      }

    // componentDidMount(){
    //     axios.get("http://localhost:8080/api/lists/"+this.state.username + "/" + this.state.type)
    //     .then(res => {
    //         this.setState({movieList: res.data})   
    //         console.log(this.state.movieList);     
    //     })
    // }
    handleInputChange = event => {
        this.setState({ [event.target.name]: event.target.value });
      };

      clickHandlerSearch = () => {
        axios.get("http://localhost:8080/api/lists/"+this.state.username + "/" + this.state.type)
        .then(res => {
            this.setState({movieList: res.data})   
            console.log(this.state.movieList);     
        })
      };

    TableCreate = () => {
        const table = [];
        for (let index = 0; index < this.state.movieList.length; index++) {
                table.push(                    
                    <tr>
                        <td>{this.state.movieList[index].name}</td>
                        <td>{this.state.movieList[index].releaseDate}</td>
                        <td>{this.state.movieList[index].imdbRating}</td>
                        <td>{this.state.movieList[index].duration}</td>
                        <td>{this.state.movieList[index].genre}</td>
                        <td>favs</td>
                    </tr> 
                );
        }
        return table;
    }

    render(){
        return(
            <div className = "outerDiv6">
                < div className="outerDiv3">
                    <span className ="usersTitle" style ={{color: "aliceblue"}}>SEARCH</span>
                    <br />
                    <input className="textL" placeholder="Username"
                    type="text"
                    name="username"
                    value={this.state.username}
                    onChange={this.handleInputChange}
                    />
                    <br />
                    <input className="textL" placeholder="List Name"
                    type="text"
                    name="type"
                    value={this.state.type}
                    onChange={this.handleInputChange}
                    />
                    <br />
                    <button className="buttonStyle" onClick={this.clickHandlerSearch}>Search</button>
                </div>
                <br/>
                <span className = "listTitle" style ={{color: "aliceblue"}}>MOVIE LISTS</span>
                <table className = "movieListTable">
                    <tr>
                        <th>Name</th>
                        <th>Release Date</th>
                        <th>IMDB Rate</th>
                        <th>Duration</th>
                        <th>Genre</th>
                        <th>List Name</th>
                    </tr>
                    <this.TableCreate/>
                </table>
                <br/>
                <Link className = "linkerMovieList" to="/main">Return to Main Page</Link>
            </div>
        );
    }
}

export default MovieList;