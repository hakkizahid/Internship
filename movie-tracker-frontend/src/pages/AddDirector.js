import React from "react";
import axios from "axios";
import "../pagesCss/AddDirectors.css";
import { Link } from "react-router-dom";
import Movies from "./Movies";
import { withRouter } from "react-router-dom";

import { Route, NavLink, Switch } from "react-router-dom";

class AddDirector extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      name: "",
      surname: "",
      birthDate: "",
      birthPlace: ""
    };
  }

  handleInputChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  clickHandler = () => {
    var obj = {
      id: this.state.id,
      name: this.state.name,
      surname: this.state.surname,
      birthDate: this.state.birthDate,
      birthPlace: this.state.birthPlace
    };
    axios.post("http://localhost:8080/api/directors", obj).then(res => {
      console.log("add successful");
    });
  };

  render() {
    return (
      <div className="outerDiv1">
        <div>
          <span className="welcome">Add Director</span>
        </div>

        <div className="divDiv">
        <span className="nameAdd">Id:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="id"
            value={this.state.id}
            onChange={this.handleInputChange}
          />
          <br />
          
          <span className="nameAdd">Name:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="name"
            value={this.state.name}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">Surname:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="surname"
            value={this.state.surname}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">Birth Date:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="birthDate"
            value={this.state.birthDate}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">Birth Place:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="birthPlace"
            value={this.state.birthPlace}
            onChange={this.handleInputChange}
          />
          <br />

          <button className="buttonAdd" onClick={this.clickHandler}>
            Add
          </button>

          <br />
          <Link className="returnlinkerDirectors" to="/directors">
            Return to Directors
          </Link>
          <br />

          <span>{this.state.resultMessage}</span>
          <br />
        </div>
      </div>
    );
  }
}

export default AddDirector;
