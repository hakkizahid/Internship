import React from "react";
import axios from 'axios';
import {Link} from 'react-router-dom';
import "../pagesCss/SearchMovie.css";

class SearchMovie extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            id: "",
            name: "",
            releaseDate: "",
            imdbRating:"",
            duration:"",
            genre:"",
            search:"",
            movies:[]
        };
      }

    // componentDidMount(){
    //     axios.get("http://localhost:8080/api/movies/"+{})
    //     .then(res => {
    //         this.setState({movies: res.data})        
    //     })
    // }

     clickHandlerDelete = () => {
         axios.delete("http://localhost:8080/api/movies/"+ this.state.movies[0].id)
         .then(
             console.log("Item is deleted")
         )
       };

       clickHandlerUpdate = () => {
        axios.put("http://localhost:8080/api/movies/"+ this.state.id)
        .then(
            console.log("Item is updated"),
            console.log(this.state.id),
            console.log(this.state.name),
            console.log(this.state.releaseDate),
        )
      };
    handleInputChange = event => {
        this.setState({ [event.target.name]: event.target.value });
      };

    clickHandlerSearch = () => {
      axios.get("http://localhost:8080/api/movies/" + this.state.search)
      .then(res => {
          this.setState({movies: res.data})        
      })
    };

    TableCreate = () => {
        const table = [];
        for (let index = 0; index < this.state.movies.length; index++) {
                table.push(                    
                    <tr>
                        <td>{this.state.movies[index].id}</td>
                        <td>{this.state.movies[index].name}</td>
                        <td>{this.state.movies[index].releaseDate}</td>
                        <td>{this.state.movies[index].imdbRating}</td>
                        <td>{this.state.movies[index].duration}</td>
                        <td>{this.state.movies[index].genre}</td>
                        <td>
                            <button className= "buttonDelete" onClick = {this.clickHandlerDelete}>Delete</button>
                        </td>
                    </tr> 
                );
        }
        return table;
    }

    TempFunction = () => {

    }

    CreateTable = () => {
        return(
            <div className = "outerDiv3">
                <span className ="moviesTitle" style ={{color: "aliceblue"}}>MOVIE</span>
                <table className = "moviesTable">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Release Date</th>
                        <th>IMDB Rate</th>
                        <th>Duration</th>
                        <th>Genre</th>
                        <th>Delete</th>
                    </tr>
                    <this.TableCreate/>
                </table>
            </div>
        );
    }

    render(){
        return(
            <div className = "outerDiv3"><br/>
                <div className="outerDiv3">
                    <span className ="moviesTitle" style ={{color: "aliceblue"}}>SEARCH</span>
                    <br/>
                    <input className="textL"
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleInputChange}
                    />
                    <br />
                    <button className="buttonStyle" onClick={this.clickHandlerSearch}>Search</button>
                </div>
                <br/>
                <br/>
                <this.CreateTable/>
                <br/>
                <div className="updateDiv">
                    <input className="textUpdate" placeholder ="Id"
                      type="text"
                      name="id"
                      value={this.state.id}
                      onChange={this.handleInputChange}
                    />
                    <input className="textUpdate" placeholder ="Name"
                      type="text"
                      name="name"
                      value={this.state.name}
                      onChange={this.handleInputChange}
                    />
                    <input className="textUpdate" placeholder ="Release Date"
                      type="text"
                      name="releaseDate"
                      value={this.state.releaseDate}
                      onChange={this.handleInputChange}
                    />
                    <input className="textUpdate" placeholder ="IMDB Rate"
                      type="text"
                      name="imdbRating"
                      value={this.state.imdbRating}
                      onChange={this.handleInputChange}
                    />
                    <input className="textUpdate" placeholder ="Duration"
                      type="text"
                      name="duration"
                      value={this.state.duration}
                      onChange={this.handleInputChange}
                    />
                    <input className="textUpdate" placeholder ="Genre"
                      type="tex"
                      name="genre"
                      value={this.state.genre}
                      onChange={this.handleInputChange}
                    />

                    <button
                      className="buttonUpdate"
                      onClick={this.clickHandlerUpdate}
                    >
                      Update
                    </button>
                    <br />
                    <br/>
                <br/>
                <Link className = "linkerMovies" to="/movies">Return to Movies</Link>
                <br/>
                <Link className = "linkerMovies" to="/main">Return to Main Page</Link>
                </div>
            </div>
        );
    }
}

export default SearchMovie;