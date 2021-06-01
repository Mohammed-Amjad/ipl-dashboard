import { React, useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Match } from '../components/Match';
import { YearsList } from '../components/YearsList';
import { SearchBox } from '../components/SearchBox';
import { Link } from 'react-router-dom';
import './MatchPage.scss';


export const MatchPage = () => {

  const [matches, setMatches] = useState([]);
  const [searchText, setSearchText] = useState('');
  const [firstYear, setFirstYear] = useState(2008);
  const [lastYear, setLastYear] = useState(2020);

  const { teamName, year } = useParams();

  useEffect(() => {

    const fetchMatches = async () => {
      const response = await fetch(`${process.env.REACT_APP_ROOT_URL}/teams/${teamName}/matches?year=${year}`);
      const data = await response.json();
      setMatches(data.matches);
      setFirstYear(data.firstYearOfTeamInData);
      setLastYear(data.lastYearOfTeamInData);
    }

    fetchMatches();
  }, [teamName, year]);

  const onSearchChange = (event) => {
    setSearchText(event.target.value);
  }

  if (!matches || matches.length === 0 || !year || !teamName) {
    return (
      <div className="MatchPage">
        <nav className="nav-bar">
          <Link to="/" className="home"><h5>Home</h5></Link>
        </nav>
        <div></div>
        <h1>Loading...</h1>
      </div>
    )
  }

  const filteredMatches = matches.filter(match =>
    match.team2.toLowerCase().includes(searchText.toLowerCase())
     || match.team1.toLowerCase().includes(searchText.toLowerCase()));

  return (
    <div className="MatchPage">
      <nav className="nav-bar">
        <Link to="/" className="home-link"><h5>Home</h5></Link>
        <SearchBox onSearchChange={onSearchChange} placeholder="Search Vs"/>
      </nav>
      <div className="years-column">
        <YearsList teamName={teamName} firstYear={firstYear} lastYear={lastYear} />
      </div>
      <div>
        <h2 className="match-page-heading">{teamName} matches in year {year}</h2>
        {
          filteredMatches.map(match => <Match match={match} teamName={teamName} key={match.id} />)
        }
      </div>
    </div>
  );
}