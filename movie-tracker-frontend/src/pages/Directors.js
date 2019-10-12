import React from "react";
import axios from 'axios';
import {Link} from 'react-router-dom';
import "../pagesCss/Directors.css";

class Directors extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
        directors:[]
        };
      }

    componentDidMount(){
        axios.get("http://localhost:8080/api/directors")
        .then(res => {
            this.setState({directors: res.data})        
        })
    }

    TableCreate = () => {
        const table = [];
        for (let index = 0; index < this.state.directors.length; index++) {
                table.push(                    
                    <tr>
                        <td>{this.state.directors[index].name}</td>
                        <td>{this.state.directors[index].surname}</td>
                        <td>{this.state.directors[index].birthDate}</td>
                        <td>{this.state.directors[index].birthPlace}</td>
                        <td>
                            <button className= "buttonUpdate">Update</button>
                        </td>
                    </tr> 
                );
        }
        return table;
    }

    render(){
        return(
            <div className = "outerDiv4">
                <span className="directorsTitle" style ={{color: "aliceblue"}}>DIRECTORS</span>
                <table className = "directorsTable">
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Birth Date</th>
                        <th>Birth Place</th>
                        <th>Update</th>
                    </tr>
                    <this.TableCreate/>
                </table>
                <br/>
                <Link className = "addlinkerMovies" to="/adddirector">ADD DIRECTOR</Link>
                <br/>
                <br/>
                <Link className = "addlinkerMovies" to="/searchdirector">SEARCH DIRECTOR</Link>
                <br/>
                <br/>
                <Link className = "linkerDirectors" to="/main">Return to Main Page</Link>
            </div>
        );
    }
}

export default Directors;