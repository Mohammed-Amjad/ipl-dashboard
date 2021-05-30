import { React, useState, useEffect } from 'react';
import { TeamTile } from '../components/TeamTile';
import { SearchBox } from '../components/SearchBox';
import './HomePage.scss';

export const HomePage = () => {

  const [teams, setTeams] = useState([]);
  const [searchText, setSearchText] = useState('');

  useEffect(() => {

    const fetchTeams = async () => {
      const response = await fetch(`${process.env.REACT_APP_ROOT_URL}/teams`);
      const data = await response.json();
      setTeams(data);
    }

    fetchTeams();
  }, []);

  const onSearchChange = (event) => {
    setSearchText(event.target.value);
  }

  if (teams.length === 0) {
    return (
      <div className="HomePage">
        <nav className="nav-bar">
          <h1>IPL Dashboard</h1>
        </nav>
        <div></div>
        <h1>Loading...</h1>
      </div>
    )
  }


  const filteredTeams = teams.filter(team => team.teamName.toLowerCase().includes(searchText.toLowerCase()));

  return (
    <div className="HomePage">
      <nav className="nav-bar">
        <h1>IPL Dashboard</h1>
        <SearchBox onSearchChange={onSearchChange} placeholder="Search Team" />
      </nav>
      <div className="teams-table">
        {filteredTeams.map(team =>
          <TeamTile key={team.id} teamName={team.teamName} />
        )}
      </div>
    </div>
  );
}