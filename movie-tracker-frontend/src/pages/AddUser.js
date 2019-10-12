import React from "react";
import axios from "axios";
import "../pagesCss/AddUser.css";
import { Link } from "react-router-dom";
import Movies from "./Movies";
import { withRouter } from "react-router-dom";

import { Route, NavLink, Switch } from "react-router-dom";

class AddUser extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      role: "",
      name: "",
      surname: "",
      username: "",
      email: ""
    };
  }

  handleInputChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  clickHandler = () => {
    var obj = {
      id: this.state.id,
      role: this.state.role,
      name: this.state.name,
      surname: this.state.surname,
      username: this.state.username,
      email: this.state.email
    };
    axios.post("http://localhost:8080/api/users", obj).then(res => {
      console.log("add successful");
    });
  };

  render() {
    return (
      <div className="outerDiv1">
        <div>
          <span className="welcome">Add User</span>
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

          <span className="nameAdd">Role:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="role"
            value={this.state.role}
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

          <span className="nameAdd">Username:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="username"
            value={this.state.username}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameAdd">E-mail:</span>
          <br />
          <input
            className="textAdd"
            type="text"
            name="email"
            value={this.state.email}
            onChange={this.handleInputChange}
          />
          <br />

          <button className="buttonAdd" onClick={this.clickHandler}>
            Add
          </button>

          <br />
          <Link className="returnlinkerMovies" to="/users">
            Return to Users
          </Link>
          <br />

          <span>{this.state.resultMessage}</span>
          <br />
        </div>
      </div>
    );
  }
}

export default AddUser;
