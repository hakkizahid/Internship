import React from "react";
import axios from "axios";
import "../pagesCss/LogIn.css";
import Movies from "./Movies";
import { withRouter } from "react-router-dom";

import { Route, NavLink, Switch } from "react-router-dom";

class LogIn extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      resultMessage: ""
    };
  }

  handleInputChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  clickHandler = () => {
    var obj = { username: this.state.username, password: this.state.password };
    axios.post("http://localhost:8080/api/login", obj).then(res => {
      console.log("Log in successful");
      this.props.history.push("/main");
    });
  };

  render() {
    return (
      <div className="outerDiv1">
        <div>
          <span className="welcome">WELCOME TO THE MOVIE TRACKER</span>
        </div>

        <div className="divDiv">
          <span className="nameL">Username:</span>
          <br />
          <input className="textL"
            type="text"
            name="username"
            value={this.state.username}
            onChange={this.handleInputChange}
          />
          <br />

          <span className="nameL">Password:</span>
          <br />
          <input className="textL"
            type="password"
            name="password"
            value={this.state.password}
            onChange={this.handleInputChange}
          />
          <br />

          <button
            className="buttonStyle"
            onClick={this.clickHandler}
          >
            Log In
          </button>
          <br />

          <span>{this.state.resultMessage}</span>
          <br />
        </div>
      </div>
    );
  }
}

export default LogIn;
