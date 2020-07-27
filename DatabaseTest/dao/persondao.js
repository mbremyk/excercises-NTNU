const Dao = require("./dao.js");

module.exports = class PersonDao extends Dao {
  getAll(callback) {
    super.query("select navn, alder, adresse from person", [], callback);
  }

  getOne(id, callback) {
    super.query(
      "select navn, alder, adresse from person where id=?",
      [id],
      callback
    );
  }

  createOne(json, callback) {
    var val = [json.navn, json.adresse, json.alder];
    super.query(
      "insert into person (navn,adresse,alder) values (?,?,?)",
      val,
      callback
    );
  }

  removeOne(id, callback) {
    super.query(
      "delete from person where id = ?",
      [id],
      callback
    );
  }

  updateOne(json, callback) {
    var query = "update person set navn = " + (json.navn ? "?" : "navn") + ", adresse = " + (json.adresse ? "?" : "adresse") + ", alder = " + (json.alder ? "?" : "alder") + " where id = ?";
    var val = [];
    if(json.navn) val.push(json.navn);
    if(json.adresse) val.push(json.adresse);
    if(json.alder) val.push(json.alder);
    if(json.id) val.push(json.id);
    super.query(
      query,
      val,
      callback
    );
  }
};
