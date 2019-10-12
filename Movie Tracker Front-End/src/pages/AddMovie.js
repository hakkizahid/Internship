import React from "react";
import axios from "axios";
import "../pagesCss/AddMovie.css";
import {Link} from 'react-router-dom';
import Movies from "./Movies";
import { withRouter } from "react-router-dom";

import { Route, NavLink, Switch } from "react-router-dom";

class AddMovie extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      name: "",
      releaseDate: "",
      imdbRating:"",
      duration:"",
      genre:""
    };
  }

  handleInputChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  clickHandler = () => {
    var obj = { id: this.state.id, name: this.state.name, releaseDate: this.state.releaseDate, imdbRating: this.state.imdbRating, duration: this.state.duration, genre: this.state.genre };
    axios.post("http://localhost:8080/api/movies", obj).then(res => {
      console.log("add successful");
    });
  };

  render() {
    return (
      <div className="outerDiv1">
        <div>
          <span className="welcome">Add Movie</span>
        </div>

        <div className="divDiv">
          <span className="nameAdd">Id:</span>
          <br />
          <input className="textAdd"
            type="text"
            name="id"
            value={this.state.id}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">Name:</span>
          <br />
          <input className="textAdd"
            type="text"
            name="name"
            value={this.state.name}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">Realase Date:</span>
          <br />
          <input className="textAdd"
            type="text"
            name="releaseDate"
            value={this.state.releaseDate}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">IMDB Rate:</span>
          <br />
          <input className="textAdd"
            type="text"
            name="imdbRating"
            value={this.state.imdbRating}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">Duration</span>
          <br />
          <input className="textAdd"
            type="text"
            name="duration"
            value={this.state.duration}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">Genre</span>
          <br />
          <input className="textAdd"
            type="tex"
            name="genre"
            value={this.state.genre}
            onChange={this.handleInputChange}
          />
          <br />

          <button
            className="buttonAdd"
            onClick={this.clickHandler}
          >
            Add
          </button>

          <br/>
            <Link className = "returnlinkerMovies" to="/movies">Return to Movies</Link>
          <br />

          <span>{this.state.resultMessage}</span>
          <br />
        </div>
      </div>
    );
  }
}

export default AddMovie;
